package com.indus.training.core.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

 @Override
 protected Class < ? > [] getRootConfigClasses() {
  return new Class[] {
  StudentDaoConfig.class
  };
 }

 @Override
 protected Class < ? > [] getServletConfigClasses() {
  return null;
 }

 @Override
 protected String[] getServletMappings() {
  return new String[] {
   "/rest/*"
  };
 }



}