package com.bookStore.tests;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:ui.properties"
})
public interface UiConfig extends Config {
    @Key("url")
    String getUrl();

    @Key("usernameManager")
    String getManagerUsername();

    @Key("passwordManager")
    String getManagerPassword();

    @Key("usernameCustomer")
    String getCustomerUsername();

    @Key("passwordCustomer")
    String getCustomerPassword();
}
