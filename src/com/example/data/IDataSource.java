package com.example.data;

import com.example.Location;

public interface IDataSource {
	public Location getLocation(int id);
	public Location getLocation(String location);
}
