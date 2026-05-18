package utils;

import net.datafaker.Faker;

public class UserFactory {

    public static UserInfo createUserInfo() {
        Faker faker = new Faker();
        UserInfo info = new UserInfo();

        info.firstName = faker.name().firstName();
        info.lastName = faker.name().lastName();
        info.street = faker.address().streetAddress();
        info.city = faker.address().city();
        info.state = faker.address().state();
        info.zipCode = faker.address().zipCode();
        info.phoneNumber = faker.phoneNumber().cellPhone(); 
        info.ssn = faker.idNumber().ssnValid();
        info.username = faker.name().username();
		info.password = faker.internet().password(12, 15, true, true, true);

        return info;
    }
}
