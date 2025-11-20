package sn.ucad.lacgaa.emploi_du_temps.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUtilisateur;

    @ManyToOne
    @JoinColumn(name = "id_region", nullable = true)
    private Region region;

    @ManyToOne
    @JoinColumn(name = "id_ville",  nullable = true)
    private Ville ville;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "utilisateur_roles",
            joinColumns = @JoinColumn(name = "id_utilisateur"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "prenom")
    private String lastName;

    @Column(name = "nom")
    private String firstName;

    @Column(name = "username")
    private String username;

    @Column(name = "email_verified")
    private Boolean emailVerified;

    @Column(name = "telephone")
    private String phoneNumber;

    @Column(name = "code_p_i_n")
    private String codePIN;

    @Column(name = "image_profil")
    private String imageProfil;

    @Column(name = "date_inscrit")
    private LocalDate dateInscrit;

    @Column(name = "date_birthday")
    private LocalDate dateBirthday;

    @Column(name = "civilite_profil")
    private String civiliteProfil;

    @Column(name = "address_profil")
    private String addressProfil;

    @Column(name = "etes_logee")
    private String etesLogee;

    @Column(name = "blog_posts")
    private Boolean blogPosts;

    @Column(name = "newsletter")
    private Boolean newsletter;

    @Column(name = "session_start")
    private String sessionStart;

    @Column(name = "last_session_activity")
    private String lastSessionActivity;

    public Utilisateur() {
    }

    public Utilisateur(Integer idUtilisateur, Region region, Ville ville, Set<Role> roles, String email, String lastName, String firstName, String username, Boolean emailVerified, String phoneNumber, String codePIN, String imageProfil, LocalDate dateInscrit, LocalDate dateBirthday, String civiliteProfil, String addressProfil, String etesLogee, Boolean blogPosts, Boolean newsletter, String sessionStart, String lastSessionActivity) {
        this.idUtilisateur = idUtilisateur;
        this.region = region;
        this.ville = ville;
        this.roles = roles;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.emailVerified = emailVerified;
        this.phoneNumber = phoneNumber;
        this.codePIN = codePIN;
        this.imageProfil = imageProfil;
        this.dateInscrit = dateInscrit;
        this.dateBirthday = dateBirthday;
        this.civiliteProfil = civiliteProfil;
        this.addressProfil = addressProfil;
        this.etesLogee = etesLogee;
        this.blogPosts = blogPosts;
        this.newsletter = newsletter;
        this.sessionStart = sessionStart;
        this.lastSessionActivity = lastSessionActivity;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCodePIN() {
        return codePIN;
    }

    public void setCodePIN(String codePIN) {
        this.codePIN = codePIN;
    }

    public String getImageProfil() {
        return imageProfil;
    }

    public void setImageProfil(String imageProfil) {
        this.imageProfil = imageProfil;
    }

    public LocalDate getDateInscrit() {
        return dateInscrit;
    }

    public void setDateInscrit(LocalDate dateInscrit) {
        this.dateInscrit = dateInscrit;
    }

    public LocalDate getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(LocalDate dateBirthday) {
        this.dateBirthday = dateBirthday;
    }

    public String getCiviliteProfil() {
        return civiliteProfil;
    }

    public void setCiviliteProfil(String civiliteProfil) {
        this.civiliteProfil = civiliteProfil;
    }

    public String getAddressProfil() {
        return addressProfil;
    }

    public void setAddressProfil(String addressProfil) {
        this.addressProfil = addressProfil;
    }

    public String getEtesLogee() {
        return etesLogee;
    }

    public void setEtesLogee(String etesLogee) {
        this.etesLogee = etesLogee;
    }

    public Boolean getBlogPosts() {
        return blogPosts;
    }

    public void setBlogPosts(Boolean blogPosts) {
        this.blogPosts = blogPosts;
    }

    public Boolean getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Boolean newsletter) {
        this.newsletter = newsletter;
    }

    public String getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(String sessionStart) {
        this.sessionStart = sessionStart;
    }

    public String getLastSessionActivity() {
        return lastSessionActivity;
    }

    public void setLastSessionActivity(String lastSessionActivity) {
        this.lastSessionActivity = lastSessionActivity;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", region=" + region +
                ", ville=" + ville +
                ", roles=" + roles +
                ", email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", emailVerified=" + emailVerified +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", codePIN='" + codePIN + '\'' +
                ", imageProfil='" + imageProfil + '\'' +
                ", dateInscrit=" + dateInscrit +
                ", dateBirthday=" + dateBirthday +
                ", civiliteProfil='" + civiliteProfil + '\'' +
                ", addressProfil='" + addressProfil + '\'' +
                ", etesLogee='" + etesLogee + '\'' +
                ", blogPosts=" + blogPosts +
                ", newsletter=" + newsletter +
                ", sessionStart='" + sessionStart + '\'' +
                ", lastSessionActivity='" + lastSessionActivity + '\'' +
                '}';
    }
}
