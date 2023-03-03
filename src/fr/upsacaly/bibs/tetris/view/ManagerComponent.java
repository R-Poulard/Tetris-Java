package fr.upsacaly.bibs.tetris.view;

import java.io.File;

import fr.upsacaly.bibs.tetris.control.manager.ManagerAction;

/**
 * An interface for all manager components
 * 
 * typicial implements would inherit from JButton or JRadioButton
 * 
 * Typically: the game manager listens to all the manager
 * components. When it received an action, it can
 * look at the action source to find the corresponding
 * manager component
 * 
 * @author Viviane Pons
 *
 */
public interface ManagerComponent {

	/**
	 * Set the Manager action attached to the component
	 * @param action
	 */
	public void setManagerAction(ManagerAction action);
	
	/**
	 * Get the Manager action attached to the component
	 * @return
	 */
	public ManagerAction getManagerAction();
	
	/**
	 * Ask the component to open file selection and select a file
	 * 
	 * If no file is selected, it returns null
	 * 
	 * @return the file if selected, null otherwise
	 */
	public File selectFile();

}
