DROP database IF exists dcmdashboard;

CREATE database dcmdashboard;

use dcmdashboard;



CREATE TABLE info_dashboard(
teamname varchar(50),
dataname varchar(50),
datavalue varchar(50),
dataunit varchar(50),
reportdate varchar(50),
primary key(teamname,dataname,reportdate));


CREATE TABLE info_prontohistory(
pr_id varchar(50) not null,
pr_title varchar(255),
pr_severity varchar(16),
pr_respperson varchar(32),
pr_rootcause varchar(8192),
pr_reportdate varchar(32),
pr_opendate varchar(32),
pr_closedate varchar(32),
primary key(pr_id));


CREATE TABLE info_pronto(
pr_id varchar(50) not null,
pr_title varchar(255),
pr_topimportance varchar(16),
pr_severity varchar(16),
pr_priority varchar(16),
pr_state varchar(16),
pr_update varchar(8192),
pr_respperson varchar(32),
pr_author varchar(64),
pr_authorgrp varchar(64),
pr_reportdate varchar(32),
primary key(pr_id));




CREATE TABLE info_utcoverage(
ut_modulename varchar(50),
ut_teamname varchar(50),
ut_coveredline varchar(50),
ut_totalline varchar(50),
ut_linecoverage varchar(50),
ut_coveredbranch varchar(50),
ut_totalbranch varchar(50),
ut_branchcoverage varchar(50),
ut_reportdate varchar(50),
ut_reportpath varchar(50),
primary key(ut_modulename,ut_teamname,ut_reportdate));

CREATE TABLE info_ci(
ci_passrate varchar(50),
ci_reportdate varchar(50),
primary key(ci_reportdate));


CREATE TABLE info_cidetail(
cidetail_tester varchar(50),
cidetail_totalcase varchar(50),
cidetail_failedcase varchar(50),
cidetail_failedversion varchar(255),
cidetail_failedcasename varchar(512),
cidetail_faileddate varchar(50),
cidetail_reportdate varchar(50),
primary key(cidetail_tester,cidetail_reportdate));

CREATE TABLE info_mt(
mt_modulename varchar(50),
mt_passrate varchar(50),
mt_reportdate varchar(50),
primary key(mt_reportdate));

CREATE TABLE info_prcount(
prcount_num varchar(50),
prcount_date varchar(50),
primary key(prcount_date));
