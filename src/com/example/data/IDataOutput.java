package com.example.data;

import java.io.IOException;

import com.example.Location;

public interface IDataOutput {
	public void genJsonFromLocations(Location lA, Location lB) throws IOException;
}
