package com.technophobics.snapchef;

import com.microsoft.projectoxford.vision.VisionServiceClient;
import com.microsoft.projectoxford.vision.VisionServiceRestClient;
import com.microsoft.projectoxford.vision.contract.AnalysisResult;
import com.microsoft.projectoxford.vision.contract.Tag;
import com.microsoft.projectoxford.vision.rest.VisionServiceException;
import com.technophobics.snapchef.data.ComparableTag;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiger on 2017-03-25.
 */

public class AnalyzeFood {
    static final String[] features = {"tags"};
    static final String[] details = {"food"};

    public static List<ComparableTag> analyze(InputStream stream) throws VisionServiceException, IOException {
        VisionServiceClient vision = new VisionServiceRestClient("");

        AnalysisResult result = vision.analyzeImage(stream, features, details);
        List<ComparableTag> list = new ArrayList<>();

        for (Tag t : result.tags) list.add(new ComparableTag(t));
        return list;
    }
}
