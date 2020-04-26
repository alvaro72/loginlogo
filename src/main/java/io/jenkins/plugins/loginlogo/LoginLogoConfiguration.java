package io.jenkins.plugins.loginlogo;

import hudson.Extension;
import hudson.util.FormValidation;
import jenkins.model.GlobalConfiguration;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

/**
 * Plugin Global configuration. With options to change welcome message and to add Extra CSS.
 * @author alvaro72
 */
@Extension
public class LoginLogoConfiguration extends GlobalConfiguration {

    /** @return the singleton instance */
    public static LoginLogoConfiguration get() {
        return GlobalConfiguration.all().get(LoginLogoConfiguration.class);
    }

    private String label;
    private String extraCss;

    public LoginLogoConfiguration() {
        load();
    }

    /** @return the currently configured label, if any */
    public String getLabel() {
        return label;
    }

    /**
     * Together with {@link #getLabel}, binds to entry in {@code config.jelly}.
     * @param label the new value of this field
     */
    @DataBoundSetter
    public void setLabel(String label) {
        this.label = label;
        save();
    }

    public FormValidation doCheckLabel(@QueryParameter String value) {
        return FormValidation.ok();
    }

    /** @return the currently configured extraCss, if any */
    public String getExtraCss() {
        return extraCss;
    }

    /**
     * Together with {@link #getExtraCss}, binds to entry in {@code config.jelly}.
     * @param extraCss the new value of this field
     */
    @DataBoundSetter
    public void setExtraCss(String extraCss) {
        this.extraCss = extraCss;
        save();
    }

    public FormValidation doCheckExtraCss(@QueryParameter String value) {
        return FormValidation.ok();
    }

}
