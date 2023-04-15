package fr.upsaclay.bibs.tetris.control.manager;

/**
 *Classe a �t� chang� et correspond au diff�rent bouttons, ceci permet de garder la vue et la actionhanlder s�par�
 * (on n'a  pas besoin de li� l'actionhandler aux bouttons de la vue et donc d'importer le actionhandler dans la vue
 */

public enum ManagerAction {
	//list d'action
	PAUSE_RESUME,//permet de faire pause ingame
	 PAUSE_QUIT,//revenir au menu lors de la pause
	END_MENU, // Boutton pour revenir au menu � la fin d'un partie
	PM2, // select le mode de joueur 2
	PM1,// select le mode de joueur 1
	GM1, // select le mode de jeu 1
	GM2, // select le mode de jeu 2
	GM3,// select le mode de jeu 3
	MENU_START, //Debute la partie
	MENU_QUIT, //Quitte le jeu
	SAVE_FILE, //Permet de sauver dans un fichier une partie
	CHOSE_FILE//load une partie depuis un fichier	
}
