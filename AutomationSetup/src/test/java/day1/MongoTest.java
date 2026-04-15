package day1;

import org.bson.Document;
import utils.MongoDBUtils;

public class MongoTest {
    public static void main(String[] args) {
        MongoDBUtils mongo = new MongoDBUtils();

        String id = "67860cbac4ec1f0bf99941fd";
        Document doc = mongo.getDocumentById(id);

        if (doc != null) {
            System.out.println("Document found: " + doc.toJson());

            // Print templateCredit field value
            Object templateCredit = doc.get("templateCredit");
            if (templateCredit != null) {
                System.out.println("templateCredit: " + templateCredit);
            } else {
                System.out.println("templateCredit field not found in this document.");
            }

        } else {
            System.out.println("No document found with that ID: " + id);
        }

        mongo.close();
    }
}
