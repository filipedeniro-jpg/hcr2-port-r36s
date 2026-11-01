package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
class Parsers {
    Parsers() {
    }

    public static class IntegerParser {
        private int defaultValue;
        private final MobileAdsLogger logger;
        private String parseErrorLogMessage;
        private String parseErrorLogTag;

        public IntegerParser() {
            this(new MobileAdsLoggerFactory());
        }

        IntegerParser(MobileAdsLoggerFactory mobileAdsLoggerFactory) {
            this.logger = mobileAdsLoggerFactory.createMobileAdsLogger("");
        }

        public IntegerParser setDefaultValue(int i) {
            this.defaultValue = i;
            return this;
        }

        public IntegerParser setParseErrorLogMessage(String str) {
            this.parseErrorLogMessage = str;
            return this;
        }

        public IntegerParser setParseErrorLogTag(String str) {
            this.parseErrorLogTag = str;
            this.logger.withLogTag(this.parseErrorLogTag);
            return this;
        }

        public int parse(String str) {
            String str2;
            int i = this.defaultValue;
            if (StringUtils.isNullOrWhiteSpace(str)) {
                return i;
            }
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                if (this.parseErrorLogTag == null || (str2 = this.parseErrorLogMessage) == null) {
                    return i;
                }
                this.logger.w(str2);
                return i;
            }
        }
    }
}
