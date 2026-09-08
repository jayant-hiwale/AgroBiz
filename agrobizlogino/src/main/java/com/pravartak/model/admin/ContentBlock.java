package com.pravartak.model.admin;

    // =========================================================
    // CONTENT BLOCK
    // =========================================================

    /**
     * Represents one piece of lesson content.
     *
     * Examples:
     *
     * TEXT
     * content = "This is some text"
     *
     * IMAGE
     * content = "https://image-url"
     *
     * VIDEO
     * content = "https://youtube.com/..."
     *
     * DOCUMENT
     * content = "https://document-url"
     */
    public  class ContentBlock {

        private String type;

        private String content;

        private int order;

        // ---------------------------------------------------------
        // FIRESTORE CONSTRUCTOR
        // ---------------------------------------------------------

        public ContentBlock() {

            this.type = "";
            this.content = "";
            this.order = 0;
        }

        // ---------------------------------------------------------
        // CONSTRUCTOR
        // ---------------------------------------------------------

        public ContentBlock(
                String type,
                String content,
                int order) {

            this.type = type;
            this.content = content;
            this.order = order;
        }

        // ---------------------------------------------------------
        // GETTERS
        // ---------------------------------------------------------

        public String getType() {
            return type;
        }

        public String getContent() {
            return content;
        }

        public int getOrder() {
            return order;
        }

        // ---------------------------------------------------------
        // SETTERS
        // ---------------------------------------------------------

        public void setType(String type) {
            this.type = type;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setOrder(int order) {
            this.order = order;
        }
    }
