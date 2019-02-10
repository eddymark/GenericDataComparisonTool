package GenericDataComparison;

import java.util.Random;

import org.json.simple.JSONObject;

public class Characteristic {
	private long id;
	private String name;
	private double minimumValue;
	private double maximumValue;
	private double averageValue;
	private double medianValue;
	private double scoreWeightValue;
	private double firstQuartile;
	private double thirdQuartile;
	private BetterValue betterValue;

	static final String _nameNode = "name";
	static final String _minimumValueNode = "minimumValue";
	static final String _firstQuartileNode = "firstQuartile";
	static final String _medianValueNode = "medianValue";
	static final String _thirdQuartileNode = "thirdQuartile";
	static final String _maximumValueNode = "maximumValue";
	static final String _averageValueNode = "averageValue";
	static final String _weightValueNode = "weightValue";
	static final String _betterValueNode = "betterValue";

	public Characteristic() {
		id = new Random().nextLong();
	}

	public Characteristic(String name, double minValue, double maxValue, double avgValue, double medianValue,
			double scoreWgtValue, double firstQrtile, double thirdQrtile, BetterValue betterValue) {
		this();
		this.name = name;
		this.minimumValue = minValue;
		this.maximumValue = maxValue;
		this.averageValue = avgValue;
		this.medianValue = medianValue;
		this.scoreWeightValue = scoreWgtValue;
		this.firstQuartile = firstQrtile;
		this.thirdQuartile = thirdQrtile;
		this.betterValue = betterValue;
	}

	public void loadCharacteristic(JSONObject data) {
		this.setName(data.get(_nameNode).toString());
		this.setMinimumValue(Double.parseDouble(data.get(_minimumValueNode).toString()));
		this.setFirstQuartile(Double.parseDouble(data.get(_firstQuartileNode).toString()));
		this.setMedianValue(Double.parseDouble(data.get(_medianValueNode).toString()));
		this.setThirdQuartile(Double.parseDouble(data.get(_thirdQuartileNode).toString()));
		this.setMaximumValue(Double.parseDouble(data.get(_maximumValueNode).toString()));
		this.setAverageValue(Double.parseDouble(data.get(_averageValueNode).toString()));
		this.setScoreWeightValue(Double.parseDouble(data.get(_weightValueNode).toString()));
		if (data.get(_betterValueNode).toString().equals("LOWEST")) {
			this.setBetterValue(BetterValue.LOWEST);
		} else {
			this.setBetterValue(BetterValue.HIGHEST);
		}
	}

	public JSONObject saveCharacteristic() {
		JSONObject jsonCharacteristic = new JSONObject();
		jsonCharacteristic.put(_nameNode, this.getName());
		jsonCharacteristic.put(_minimumValueNode, this.getMinimumValue());
		jsonCharacteristic.put(_firstQuartileNode, this.getFirstQuartile());
		jsonCharacteristic.put(_medianValueNode, this.getMedianValue());
		jsonCharacteristic.put(_thirdQuartileNode, this.getThirdQuartile());
		jsonCharacteristic.put(_maximumValueNode, this.getMaximumValue());
		jsonCharacteristic.put(_averageValueNode, this.getAverageValue());
		jsonCharacteristic.put(_weightValueNode, this.getScoreWeightValue());
		jsonCharacteristic.put(_betterValueNode, this.getBetterValue().toString());
		return jsonCharacteristic;
	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public double getMinimumValue() {
		return minimumValue;
	}

	public double getMaximumValue() {
		return maximumValue;
	}

	public double getAverageValue() {
		return averageValue;
	}

	public double getMedianValue() {
		return medianValue;
	}

	public double getScoreWeightValue() {
		return scoreWeightValue;
	}

	public double getFirstQuartile() {
		return firstQuartile;
	}

	public double getThirdQuartile() {
		return thirdQuartile;
	}

	public BetterValue getBetterValue() {
		return betterValue;
	}

	public void setId(long id) {
		this.id = id;

	}

	public void setName(String name) {
		this.name = name;

	}

	public void setMinimumValue(double minValue) {
		this.minimumValue = minValue;
	}

	public void setMaximumValue(double maxValue) {
		this.maximumValue = maxValue;

	}

	public void setAverageValue(double avgValue) {
		this.averageValue = avgValue;

	}

	public void setMedianValue(double medValue) {
		this.medianValue = medValue;
	}

	public void setScoreWeightValue(double scoreWgtValue) {
		this.scoreWeightValue = scoreWgtValue;
	}

	public void setFirstQuartile(double firstQrtile) {
		this.firstQuartile = firstQrtile;

	}

	public void setThirdQuartile(double thirdQrtile) {
		this.thirdQuartile = thirdQrtile;
	}

	public void setBetterValue(BetterValue betterValue) {
		this.betterValue = betterValue;
	}

	public double calculateScore(double userValue) {
		double range = maximumValue - minimumValue;
		double increment = 100.0 / range;
		double rawScore;
		if (betterValue == BetterValue.HIGHEST) {
			rawScore = userValue - minimumValue;
		} else {
			rawScore = maximumValue - userValue;
		}

		return rawScore * increment * (scoreWeightValue / 100);
	}
}
