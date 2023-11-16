package org.makerminds.jcoaching.internship.restaurantpoint.gui.view;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * manages connections between view identifiers and {@link IView}
 * implementations
 * 
 * @author makerminds
 *
 */
public final class ViewFactory {

	private static final ViewFactory INSTANCE = new ViewFactory(); 
	
	private static Map<ViewIdentifier, IViewCreator> VIEWIDENTIFIER_TO_CREATOR;

	private ViewFactory() {
		initializeIViewCreatorMap();
	}

	private void initializeIViewCreatorMap() {
		VIEWIDENTIFIER_TO_CREATOR = new HashMap<ViewIdentifier, IViewCreator>();

		ServiceLoader<IViewCreator> viewCreators = ServiceLoader.load(IViewCreator.class,
				IViewCreator.class.getClassLoader());
		for (IViewCreator viewCreator : viewCreators) {
			// add mapping of view identifier and view creator into the map
			VIEWIDENTIFIER_TO_CREATOR.put(viewCreator.getViewIdentifier(), viewCreator);
		}
	}

	public static ViewFactory getInstance() {
		return INSTANCE;
	}
	
	public IView getViewByViewIdentifier(ViewIdentifier viewIdentifier) {
		IViewCreator viewCreator = VIEWIDENTIFIER_TO_CREATOR.get(viewIdentifier);
		if(viewCreator == null) {
			throw new IllegalStateException("No View for View Identifier " + viewIdentifier + " configured!");
		}
		
		IView view = viewCreator.createView();
		
		return view;
	}
}
