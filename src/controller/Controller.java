package controller;

import model.Model;

public interface Controller {
	void setModel(Model model);
	void onClick(int x, int y);
}
