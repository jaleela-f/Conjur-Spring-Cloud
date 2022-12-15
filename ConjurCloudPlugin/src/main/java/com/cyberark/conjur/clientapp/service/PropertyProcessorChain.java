package com.cyberark.conjur.clientapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;

import com.cyberark.conjur.clientapp.domain.ConjurAuthParam;


public abstract class PropertyProcessorChain extends  EnumerablePropertySource<Object>{
	
	private PropertyProcessorChain processorChain;
	private ConjurAuthParam conjurParam;
	
	public PropertyProcessorChain(String name,ConjurAuthParam conjurParam) {
		super("propertyProcessorChain");
		this.conjurParam= conjurParam;
		
	}
	public void setNextChain(PropertyProcessorChain processChain)
	{
		this.processorChain = new DefaultPropertySourceChain(conjurParam);

		CustomPropertySourceChain customPS = new CustomPropertySourceChain(conjurParam);
		processorChain.setNextChain(customPS);
	}
	
	@Override
	public Object getProperty(String name)
	{
		return name;
		
	}
	

}
