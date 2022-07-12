/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: specifications/WriteService.java 2015-03-11 buixuan.
 * ******************************************************/
package interfaces;

import tools.User;

import java.awt.*;

public interface WriteService {
    public  void  setFood(Point p);
    public void setGameOver(Boolean value);
    public void  incrementScore(int value);
    public void setDirection(User.COMMAND C);
    public void incrementSize();

}
