package com.yangworld.app.domain.photoFeed.service;

import com.yangworld.app.domain.photoFeed.entity.PeedDetails;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;

public interface PhotofeedService {

	int insertPeed(PeedDetails peed);

	PhotoFeed selectFeed(String name);

}
