package creativeeditor.data;

import creativeeditor.data.base.DataFloat;
import lombok.Getter;
import net.minecraft.util.math.MathHelper;

public class NumberRangeFloat extends DataFloat {
    protected @Getter
    float min, max;


    public NumberRangeFloat(float number, float min, float max) {
        super(number);
        this.min = min;
        this.max = max;
    }


    public NumberRangeFloat(float min, float max) {
        this(min, min, max);
    }


    @Override
    public void set(Float number) {
        super.set(MathHelper.clamp(number, getMin(), getMax()));
    }
}
