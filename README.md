# Login Logo Plugin
This Jenkins plugin adds a custom logo to your Jenkins login page. It's also allows to change welcome message and add Extra Css to Jenkins Login Page.

## How to Use
Clone this repository.

    $ git clone https://github.com/alvaro72/loginlogo.git

### Copy images
Copy new logo image in png format replacing **logo.png** (grape image) in **webapp** folder.

    $ cp newlogo.png src/main/webapp/logo.png

Copy any other images to webapp folder (optional).

    $ cp background.png src/main/webapp/

### Build Jenkins plugin
Build hpi jenkins package.

    $ mvn package

### Install login-logo plugin

To install plugin in Jenkins, go to Manage Jenkins and then to Manage Plugins, then go to Advanced Configuration and Upload a Plugin.
Then file browse to _target/login-logo.hpi_ file.
Finally restart Jenkins.

### Configure Login logo plugin

To configure the plugin, go to **Manage Jenkins** and then to **System Configuration**.

Scroll down to **Login Logo Section**.

There you can change Welcome Jenkins message and add Extra CSS on Login Page.

For example if you want to use images in Extra CSS, here is how to do with background.png.

```
body {
    background: url('plugin/login-logo/background.png');
    background-size: cover;
}
```

## Author
@alsagui

