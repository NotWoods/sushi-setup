package com.technophobics.snapchef.data;

import com.microsoft.projectoxford.vision.contract.Tag;

/**
 * Created by tiger on 2017-03-26.
 */

public class ComparableTag extends Tag implements Comparable<ComparableTag> {
    public ComparableTag(Tag base) {
        confidence = base.confidence;
        hint = base.hint;
        name = base.name;
    }

    public int compareTo(ComparableTag other) {
        return this.name.compareTo(other.name);
    }
}
