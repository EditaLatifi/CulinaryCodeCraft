package org.makerminds.jcoaching.internship.restaurantpoint.gui.view;

/**
 * Interface for classes, that has mapping of {@link ViewIdentifier} and the corresponding {@link IView} implementation.
 * 
 * @author makerminds
 *
 */
public interface IViewCreator {
	
	ViewIdentifier getViewIdentifier();
	
	IView createView();

}
