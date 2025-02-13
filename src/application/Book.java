package application;

public record Book(String authorFirstname, String authorSurname, String isbn, int publishYear) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder{
        private String authorFirstName;
        private String authorSurname;
        private String isbn;
        private int publishYear;

        public Builder authorFirstName(String authorFirstName) {
            this.authorFirstName = authorFirstName;
            return this;
        }

        public Builder authorSurname(String authorSurname) {
            this.authorSurname = authorSurname;
            return this;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder publishYear(int publishYear) {
            this.publishYear = publishYear;
            return this;
        }

        public Book build() {
            return new Book(authorFirstName,authorSurname,isbn,publishYear);
        }
    }
}
