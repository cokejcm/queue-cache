create schema security;

drop table if exists oauth_client_details;
create table security.oauth_client_details (
  client_id TEXT PRIMARY KEY,
  resource_ids TEXT,
  client_secret TEXT,
  scope TEXT,
  authorized_grant_types TEXT,
  web_server_redirect_uri TEXT,
  authorities TEXT,
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information TEXT,
  autoapprove TEXT
);
 
drop table if exists oauth_client_token;
create table security.oauth_client_token (
  authentication_id TEXT PRIMARY KEY,
  token_id TEXT,
  token BIT VARYING(2000),
  user_name TEXT,
  client_id TEXT
);
 
drop table if exists oauth_access_token;
create table security.oauth_access_token (
  token_id TEXT,
  token BIT VARYING(2000),
  authentication_id TEXT PRIMARY KEY,
  user_name TEXT,
  client_id TEXT,
  authentication  BIT VARYING(2000),
  refresh_token TEXT
);
 
drop table if exists oauth_refresh_token;
create table security.oauth_refresh_token (
  token_id TEXT,
  token  BIT VARYING(2000),
  authentication  BIT VARYING(2000)
);
 
drop table if exists oauth_code;
create table security.oauth_code (
  code TEXT, 
  authentication  BIT VARYING(2000)
);
 
drop table if exists oauth_approvals;
create table security.oauth_approvals (
    userId TEXT,
    clientId TEXT,
    scope TEXT,
    status TEXT,
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);
 
drop table if exists ClientDetails;
create table security.ClientDetails (
  appId TEXT PRIMARY KEY,
  resourceIds TEXT,
  appSecret TEXT,
  scope TEXT,
  grantTypes TEXT,
  redirectUrl TEXT,
  authorities TEXT,
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation TEXT,
  autoApproveScopes TEXT
);