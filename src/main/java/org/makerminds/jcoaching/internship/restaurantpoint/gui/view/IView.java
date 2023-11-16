package org.makerminds.jcoaching.internship.restaurantpoint.gui.view;

/**
 * interface for application views.
 * 
 * @author makerminds
 *
 */
public interface IView {
	
	/**
	 * prepares the view with all its components
	 */
	public void prepareView();
	
	/**
	 * refreshes the view data to react on changes made in the model
	 */
	public void refreshViewData();

}
