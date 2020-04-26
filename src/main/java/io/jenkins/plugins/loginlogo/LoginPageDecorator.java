package io.jenkins.plugins.loginlogo;

import hudson.Extension;
import jenkins.model.SimplePageDecorator;

/**
 * Class that inject CSS in jelly Jenkins Login Page
 * @author alvaro72
 */
@Extension
public class LoginPageDecorator extends SimplePageDecorator {
    private String head;
    private String header;
    private String footer;

    public String getHead() {
        return head;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    /**
     * Return the configured welcome message
     * @return String with the configured welcome message
     */
    public String getProductName() {
        LoginLogoConfiguration sc = LoginLogoConfiguration.get();

        return sc.getLabel();
    }

    /**
     * Return the configured Extra CSS
     * @return String with the configured Extra CSS
     */
    public String getExtraCss() {
        LoginLogoConfiguration sc = LoginLogoConfiguration.get();

        return sc.getExtraCss()!=null ? sc.getExtraCss() : "";
    }
}