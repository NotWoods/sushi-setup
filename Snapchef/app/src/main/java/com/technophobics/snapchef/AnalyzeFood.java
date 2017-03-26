package com.technophobics.snapchef;

import com.microsoft.projectoxford.vision.VisionServiceClient;
import com.microsoft.projectoxford.vision.VisionServiceRestClient;
import com.microsoft.projectoxford.vision.contract.AnalysisResult;
import com.microsoft.projectoxford.vision.contract.Tag;
import com.microsoft.projectoxford.vision.rest.VisionServiceException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by tiger on 2017-03-25.
 */

public class AnalyzeFood {
    static final String[] features = {"tags"};
    static final String[] details = {"food"};

    List<Tag> analyze(InputStream stream) {
        VisionServiceClient vision = new VisionServiceRestClient("");

        try {
            AnalysisResult result = vision.analyzeImage(stream, features, details);
            return result.tags;
        } catch (VisionServiceException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }


}
