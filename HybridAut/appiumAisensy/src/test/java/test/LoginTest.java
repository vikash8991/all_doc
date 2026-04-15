package test;

@Test
public void testLogin() {
    LoginPage login = new LoginPage();

    login.enterUsername("vikash");
    login.enterPassword("123456");
    login.tapLogin();
}
