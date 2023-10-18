package testWeb.dao;

import java.util.List;

import testWeb.vo.RobotInfo;

public interface RobotDAO {
	public List<RobotInfo> returnrobot();
	public boolean checknull();

}
