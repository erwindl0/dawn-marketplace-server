/*****************************************************************************
 * Copyright (c) 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Torkild U. Resheim - initial API and implementation
 ****************************************************************************/
package org.dawnsci.marketplace.services;

import org.dawnsci.marketplace.Catalogs;
import org.dawnsci.marketplace.Featured;
import org.dawnsci.marketplace.Marketplace;
import org.dawnsci.marketplace.MarketplaceFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author Torkild U. Resheim, Itema AS
 */
@Service
public class DataService {
	
	@Autowired
	private SessionFactory sessionFactory;
		
	public Marketplace getContent(String identifier) {
		// TODO: Realize data from a persistent store		
		return null;
	}

	@SuppressWarnings("unchecked")
	public Marketplace getCatalogs() {
		Marketplace marketplace = MarketplaceFactory.eINSTANCE.createMarketplace();	
		Catalogs catalogs = MarketplaceFactory.eINSTANCE.createCatalogs();
		marketplace.setCatalogs(catalogs);		
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT catalog FROM Catalog catalog");
		catalogs.getItems().addAll(query.list());
		session.close();
		return marketplace;
	}

	@SuppressWarnings("unchecked")
	public Marketplace getMarkets() {
		Marketplace marketplace = MarketplaceFactory.eINSTANCE.createMarketplace();	
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT market FROM Market market");
		marketplace.getMarkets().addAll(query.list());
		session.close();
		return marketplace;
	}

	/**
	 * Returns a server-defined number of featured results.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Marketplace getFeatured() {		
		Marketplace marketplace = MarketplaceFactory.eINSTANCE.createMarketplace();	
		Featured featured = MarketplaceFactory.eINSTANCE.createFeatured();
		marketplace.setFeatured(featured);		
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("SELECT node FROM Node node");
		featured.getNodes().addAll(query.list());
		session.close();
		return marketplace;
	}


}