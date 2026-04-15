package utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.util.Collection;

import static com.mongodb.client.model.Filters.eq;

public class MongoDBUtils {

    private MongoClient mongoClient;
    private MongoCollection<Document> collection;

    public MongoDBUtils() {
        try {
            // Absolute path to your CA certificate PEM file
            String caFilePath = "C:\\Users\\Vikas\\Downloads\\Automation_code-main\\Automation_code-main\\AutomationSetup\\global.pem";

            // Load the DocumentDB root CA certificate from PEM file
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            FileInputStream fis = new FileInputStream(caFilePath);

            // Load all certificates from the PEM bundle
            Collection<? extends java.security.cert.Certificate> certs = cf.generateCertificates(fis);
            fis.close();

            if (certs.isEmpty()) {
                throw new IllegalStateException("No certificates found in the PEM file.");
            }

            // Create a new empty keystore and load all certs
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null, null);

            int index = 1;
            for (java.security.cert.Certificate cert : certs) {
                ks.setCertificateEntry("docdb-ca-cert-" + index, cert);
                index++;
            }

            // Create a TrustManager that trusts the CA in our keystore
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ks);

            // Create SSLContext with our TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);

            // MongoDB connection string (without tlsCAFile param)
            String connectionString = "mongodb://aisensystgv1:oLGhceW3c356@aisensystaging.cluster-czxp9lu158vo.ap-south-1.docdb.amazonaws.com:27017/AiSensy?retryWrites=false&w=majority&readPreference=secondaryPreferred&authSource=admin&tls=true";

            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .applyToSslSettings(builder -> builder.enabled(true).context(sslContext))
                    .applyToClusterSettings(builder -> builder.serverSelectionTimeout(5000, java.util.concurrent.TimeUnit.MILLISECONDS))
                    .retryWrites(false)
                    .build();

            mongoClient = MongoClients.create(settings);
            MongoDatabase database = mongoClient.getDatabase("AiSensy");
            collection = database.getCollection("assistants");
            database.runCommand(new Document("ping", 1));

            System.out.println("Connected to DocumentDB successfully!");

        } catch (Exception e) {
            System.err.println("Error connecting to DocumentDB:");
            e.printStackTrace();
        }
    }

    public Document getDocumentById(String id) {
        try {
            return collection.find(eq("_id", new org.bson.types.ObjectId(id))).first();
        } catch (Exception e) {
            System.err.println("Error fetching document by ID:");
            e.printStackTrace();
            return null;
        }
    }
    
    public Object getTemplateCreditById(String id) {
        try {
            Document doc = getDocumentById(id);
            if (doc != null) {
                return doc.get("templateCredit");
            }
        } catch (Exception e) {
            System.err.println("Error retrieving templateCredit:");
            e.printStackTrace();
        }
        return null;
    }


    // ✅ Move this method OUTSIDE the constructor
    public void printTemplateCredit() {
        try {
            Document firstDoc = collection.find().first();
            if (firstDoc != null) {
                Object templateCredit = firstDoc.get("templateCredit");
                if (templateCredit != null) {
                    System.out.println("templateCredit: " + templateCredit);
                } else {
                    System.out.println("'templateCredit' field not found in the document.");
                }
            } else {
                System.out.println("No documents found in the assistants collection.");
            }
        } catch (Exception e) {
            System.err.println("Error fetching templateCredit:");
            e.printStackTrace();
        }
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("MongoClient closed.");
        }
    }

    public static void main(String[] args) {
        MongoDBUtils dbUtils = new MongoDBUtils();
        try {
            dbUtils.printTemplateCredit();

            // Optional: test specific document
            Document doc = dbUtils.getDocumentById("67860cbac4ec1f0bf99941fd");
            if (doc != null) {
                System.out.println("Found document: " + doc.toJson());
            } else {
                System.out.println("No document found with the given ID");
            }
        } finally {
            dbUtils.close();
        }
    }
}
