package uk.co.benjiweber.testexamples;

public class FoxBuilder {
	public enum Speed {QUICK}
	public enum Colour {BROWN}
	public enum Gender {MALE}
	public FoxBuilder speed(Speed speed) {
		return this;
	}
	public FoxBuilder colour(Colour colour) {
		return this;
	}
	public FoxBuilder gender(Gender colour) {
		return this;
	}
	public FoxBuilder legs(int legs) {
		return this;
	}
	public FoxBuilder longTail() {
		return this;
	}
	public Fox build() {
		return new Fox();
	}
}
