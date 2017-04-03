package com.alt.preference;

/**
 * Created by student on 31/01/17.
 */

public class BankDetailsModel {


    private int id;
    private int user_id;
    private String account_nickname;
    private String name_on_account;
    private String account_type;
    private String bank_name;
    private String bank_code;
    private String account_number;
    private String branch_code;
    private String created_at;
    private String updated_at;

    public BankDetailsModel(int id, int user_id, String account_nickname, String name_on_account, String account_type, String bank_name, String bank_code, String account_number, String branch_code, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.account_nickname = account_nickname;
        this.name_on_account = name_on_account;
        this.account_type = account_type;
        this.bank_name = bank_name;
        this.bank_code = bank_code;
        this.account_number = account_number;
        this.branch_code = branch_code;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAccount_nickname() {
        return account_nickname;
    }

    public void setAccount_nickname(String account_nickname) {
        this.account_nickname = account_nickname;
    }

    public String getName_on_account() {
        return name_on_account;
    }

    public void setName_on_account(String name_on_account) {
        this.name_on_account = name_on_account;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
