package fpstracker.utils;

public interface MathsUtils {
	public static float normalize(Number value, Number min, Number max) {
		  float valuef = ((Number)value).floatValue();
		  float minf = ((Number)min).floatValue();
		  float maxf = ((Number)max).floatValue();
		  float normf = 1.0f * ((valuef - minf) / (maxf -  minf));
		  return  (normf <= 1.0f) ? normf : 1.0f;
	}
}
