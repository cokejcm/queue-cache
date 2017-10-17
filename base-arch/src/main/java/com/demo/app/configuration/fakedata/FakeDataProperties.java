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
}
