package kz.qa.jft.addressbook.model;

public class ContactData {
    private final String id;
    private final String firstname;
    private final String lastname;
    private final String nickname;
    private final String address;
    private final String mobile;
    private final String email;
    private final String bYear;
    private String group;

    public ContactData(String id, String lastname, String firstname, String nickname, String address, String mobile, String email, String bYear, String group) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.bYear = bYear;
        this.group = group;
    }

    public ContactData(String lastname, String firstname, String nickname, String address, String mobile, String email, String bYear, String group) {
        this.id = null;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.bYear = bYear;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getbYear() {
        return bYear;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
