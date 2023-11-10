module bookTracker.restserver{
    requires com.fasterxml.jackson.databind;

    requires spring.web;
    requires spring.beans;
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;

    requires bookTracker.core;

    opens restserver to spring.beans, spring.context, spring.web;
}