package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canFindWhiskyByYear() {
		List<Whisky> found = whiskyRepository.findWhiskyByYear(1991);
		assertEquals(1, found.size());
		assertEquals("The Rosebank 12 - Flora and Fona", found.get(0).getName());
	}

	@Test
	public void canFindDistilleryByRegion() {
		List<Distillery> found = distilleryRepository.findByRegion("Highland");
		assertEquals(3, found.size());
	}

	@Test
	public void canFindWhiskyByDistilleryNameAndAge() {
		List<Whisky> found = whiskyRepository.findWhiskiesByDistilleryNameAndAge("Glendronach", 15);
		assertEquals(1, found.size());
		assertEquals("The Glendronach Revival", found.get(0).getName());
	}

	@Test
	public void canFindWhiskyByDistilleryRegion() {
		List<Whisky> found = whiskyRepository.findWhiskiesByDistilleryRegion("Highland");
		assertEquals(7, found.size());
	}

	@Test
	public void canFindDistilleriesByWhiskyAge() {
		List<Distillery> found = distilleryRepository.findByWhiskies_Age(12);
		assertEquals(6, found.size());
	}

}
