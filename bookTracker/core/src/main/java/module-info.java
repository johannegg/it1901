module bookTracker.core {
    exports core;
    requires transitive com.fasterxml.jackson.databind;
    requires transitive com.fasterxml.jackson.core;
    exports json;
}
