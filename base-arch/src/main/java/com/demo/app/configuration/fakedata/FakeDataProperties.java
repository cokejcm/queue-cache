package com.demo.app.configuration.fakedata;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Profile({"dev"})
@Component
@ConfigurationProperties(prefix="fakedata")
@PropertySource("classpath:fakedata.properties")
public class FakeDataProperties {

	private int user = 2;
	private int userIter = 1;
	private int authority = 2;
	private int authorityIter = 1;
	private int company = 2;
	private int companyIter = 1;
	private int supplier = 2;
	private int supplierIter = 1;
	private int organization = 2;
	private int organizationIter = 1;
	private int userOrganization = 2;
	private int userOrganizationIter = 1;
	private int serviceType = 2;
	private int serviceTypeIter = 1;
	private int form = 2;
	private int formIter = 1;
	private int section= 1;
	private int sectionIter = 2;
	private int questionType = 1;
	private int questionTypeIter = 2;
	private int questionValues= 1;
	private int questionValuesIter = 2;
	private int question= 1;
	private int questionIter = 2;
	private int document= 1;
	private int documentIter = 2;


	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getUserIter() {
		return userIter;
	}
	public void setUserIter(int userIter) {
		this.userIter = userIter;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public int getAuthorityIter() {
		return authorityIter;
	}
	public void setAuthorityIter(int authorityIter) {
		this.authorityIter = authorityIter;
	}
	public int getCompany() {
		return company;
	}
	public void setCompany(int company) {
		this.company = company;
	}
	public int getCompanyIter() {
		return companyIter;
	}
	public void setCompanyIter(int companyIter) {
		this.companyIter = companyIter;
	}
	public int getSupplier() {
		return supplier;
	}
	public void setSupplier(int supplier) {
		this.supplier = supplier;
	}
	public int getSupplierIter() {
		return supplierIter;
	}
	public void setSupplierIter(int supplierIter) {
		this.supplierIter = supplierIter;
	}
	public int getOrganization() {
		return organization;
	}
	public void setOrganization(int organization) {
		this.organization = organization;
	}
	public int getOrganizationIter() {
		return organizationIter;
	}
	public void setOrganizationIter(int organizationIter) {
		this.organizationIter = organizationIter;
	}
	public int getUserOrganization() {
		return userOrganization;
	}
	public void setUserOrganization(int userOrganization) {
		this.userOrganization = userOrganization;
	}
	public int getUserOrganizationIter() {
		return userOrganizationIter;
	}
	public void setUserOrganizationIter(int userOrganizationIter) {
		this.userOrganizationIter = userOrganizationIter;
	}
	public int getServiceType() {
		return serviceType;
	}
	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}
	public int getServiceTypeIter() {
		return serviceTypeIter;
	}
	public void setServiceTypeIter(int serviceTypeIter) {
		this.serviceTypeIter = serviceTypeIter;
	}
	public int getForm() {
		return form;
	}
	public void setForm(int form) {
		this.form = form;
	}
	public int getFormIter() {
		return formIter;
	}
	public void setFormIter(int formIter) {
		this.formIter = formIter;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public int getSectionIter() {
		return sectionIter;
	}
	public void setSectionIter(int sectionIter) {
		this.sectionIter = sectionIter;
	}
	public int getQuestionType() {
		return questionType;
	}
	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}
	public int getQuestionTypeIter() {
		return questionTypeIter;
	}
	public void setQuestionTypeIter(int questionTypeIter) {
		this.questionTypeIter = questionTypeIter;
	}
	public int getQuestionValues() {
		return questionValues;
	}
	public void setQuestionValues(int questionValues) {
		this.questionValues = questionValues;
	}
	public int getQuestionValuesIter() {
		return questionValuesIter;
	}
	public void setQuestionValuesIter(int questionValuesIter) {
		this.questionValuesIter = questionValuesIter;
	}
	public int getQuestion() {
		return question;
	}
	public void setQuestion(int question) {
		this.question = question;
	}
	public int getQuestionIter() {
		return questionIter;
	}
	public void setQuestionIter(int questionIter) {
		this.questionIter = questionIter;
	}
	public int getDocument() {
		return document;
	}
	public void setDocument(int document) {
		this.document = document;
	}
	public int getDocumentIter() {
		return documentIter;
	}
	public void setDocumentIter(int documentIter) {
		this.documentIter = documentIter;
	}
}
