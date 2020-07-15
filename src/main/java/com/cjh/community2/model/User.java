package com.cjh.community2.model;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.accountid
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    private String accountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.name
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.token
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.gmtcreate
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    private Long gmtcreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.gmtmodified
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    private Long gmtmodified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.avatar_url
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    private String avatar_url;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.accountid
     *
     * @return the value of user.accountid
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    public String getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.accountid
     *
     * @param accountid the value for user.accountid
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.name
     *
     * @return the value of user.name
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.name
     *
     * @param name the value for user.name
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.token
     *
     * @return the value of user.token
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.token
     *
     * @param token the value for user.token
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.gmtcreate
     *
     * @return the value of user.gmtcreate
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    public Long getGmtcreate() {
        return gmtcreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.gmtcreate
     *
     * @param gmtcreate the value for user.gmtcreate
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    public void setGmtcreate(Long gmtcreate) {
        this.gmtcreate = gmtcreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.gmtmodified
     *
     * @return the value of user.gmtmodified
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    public Long getGmtmodified() {
        return gmtmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.gmtmodified
     *
     * @param gmtmodified the value for user.gmtmodified
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    public void setGmtmodified(Long gmtmodified) {
        this.gmtmodified = gmtmodified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.avatar_url
     *
     * @return the value of user.avatar_url
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    public String getAvatar_url() {
        return avatar_url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.avatar_url
     *
     * @param avatar_url the value for user.avatar_url
     *
     * @mbg.generated Sat Jul 11 21:45:02 CST 2020
     */
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url == null ? null : avatar_url.trim();
    }
}