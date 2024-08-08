/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.project.androidairportr;

import static com.scandit.datacapture.id.data.IdImageType.ID_BACK;

import android.content.Context;

import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.id.capture.IdCapture;
import com.scandit.datacapture.id.capture.IdCaptureSettings;
import com.scandit.datacapture.id.data.IdDocumentType;
import com.scandit.datacapture.id.data.SupportedSides;

/*
 * Initializes DataCapture.
 */
public final class DataCaptureManager {
    // Enter your Scandit License key here.
    // Your Scandit License key is available via your Scandit SDK web account.
    private static final String SCANDIT_LICENSE_KEY = "AQ8AuJOBBwvsCRwW2AKCXBIGYmSDKSbjpVfE/rNVPXpwbZWxzyFvPK9FqSrqf6rUCVI2KwxVunWUGYhB32aW3udJVZLpUT79aQb1FcxNVrwaQHCvI1nfAcdc33UUbql/kXXsecgDWO9xIfH8vEIMQCsyeMBETJRfKmZ3euBEy0B+RATSdmCbfzxvcWIARN5+PEoPPnNTlWEGVZ+Nf0fvenhctk8qXEX4g02ucmNoJHbxfO+QRFnO7JB5k7GgZRWEMBTjJrZbZQPhTDWHB3F2vHlSkMBhTpIeIGObSml2rPFDMNNNOmrTuvhoOhlkE9T5vxWizttgaP5wZPEI2Bjepbt+NKXKclGIO34z8upwsb3fDIh6P09Q0dRC52XnGaIUnDdx6xJE6lvAQPhLeVCuaWwopYZueSQlxX4Y/BB0yW0QaVGN+jcoGakVwsMvd7eJVV8E6iMGP2tkbUF97mFbZV41u1R5UD2d+n3x5TYR+CIUB0ftKi5PzfFXGxTvT3if8G6KTEdu3ka1Nb6aGWCom71alzSID+MQczpTwY0tHGrJOBSEJG2GJrk4zyyhMRA5h1Jc9yFu0l9DHIw2EXvWSFp1JlcCSHxssDPlr0x9k8RzcZn83B8i0khmA1zyffqUGWoPjcxEa7bjRdMbIRv5EHyMXAGLpe0ufYma3bBeXHaFNgorwRdFHi7KtQIYL2EaaC8y24PwQe+/kSvE0LADJcm5EKXYBpvPiIAZdLGk5kSlnANYqxNOzfoO5fQAzm8R/I8JN2lgPxey5Q7jNKN+d2+xvkr1oiFDWmSxe1XluqdYu1WzcFNer1tU2OcLHlFGZ/pUh6VyUskiS/TeQ2+qfoVyUu1VoaYIIRhqKp2WVj5ZB7piRipZNM13pHCIO9CzgZfUIo1QBl567i+9Ym62Um6CHZ0D9LqlbMmPpYqA3edkZ9IQW7KIZ5tGbi0Una2nx/BLXoXgl0SWMwU8MHd2uFJXunHFlYERnMDJp4khhj+ZmLSlxgZprcibCkxax9B7v9gLAQtj4uerUgBNsFHPfwKlSdQ1va+dVESmDhEh/bmFoFNZcICT6esjjOQFGDy+VZvRJZrC+W8RWL1AbbP+Clqt+nsZsMhcHBxsyg53PfWInoB3auFESRv5zVozGZm0wNCg2FF3Y34Zgc10Cw2yhXVQJhA7gfwtkc0Edl3HQbJ3HLdh3bnuR3VvrX8Ev++zV74eiBoLYLNziH3XHcn9M1/ySJdm62WFx1Mru+STiZfR62Mz5nmmHDlFQ3OhvkXEK5/r359J9TYB6mK2+6+isbJp692HApQrxQlFpaVX/zTuVZHeBD2nAua6Hg==";

    private static DataCaptureManager INSTANCE;

    private DataCaptureContext dataCaptureContext;
    private Camera camera;
    private IdCapture idCapture;

    public static DataCaptureManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DataCaptureManager(context.getApplicationContext());
        }

        return INSTANCE;
    }

    private DataCaptureManager(Context context) {
        /*
         * Create DataCaptureContext using your license key.
         */
        dataCaptureContext = DataCaptureContext.forLicenseKey(SCANDIT_LICENSE_KEY);

        initCamera();
        initIdCapture();
    }

    private void initCamera() {
        /*
         * Set the device's default camera as DataCaptureContext's FrameSource. DataCaptureContext
         * passes the frames from it's FrameSource to the added modes to perform capture or
         * tracking.
         *
         * Since we are going to perform TextCapture in this sample, we initiate the camera with
         * the recommended settings for this mode.
         */
        camera = Camera.getDefaultCamera(IdCapture.createRecommendedCameraSettings());

        if (camera == null) {
            throw new IllegalStateException("Failed to init camera!");
        }

        dataCaptureContext.setFrameSource(camera);
    }

    private void initIdCapture() {
        /*
         * Create a mode responsible for recognizing documents. This mode is automatically added
         * to the passed DataCaptureContext.
         */
        IdCaptureSettings settings = new IdCaptureSettings();
        // Recognize national ID cards & driver's licenses.
        settings.setSupportedDocuments(
                IdDocumentType.ID_CARD_VIZ,
                IdDocumentType.DL_VIZ
        );
        settings.setSupportedSides(SupportedSides.FRONT_AND_BACK);

        idCapture = IdCapture.forDataCaptureContext(dataCaptureContext, settings);
    }

    public DataCaptureContext getDataCaptureContext() {
        return dataCaptureContext;
    }

    public Camera getCamera() {
        return camera;
    }

    public IdCapture getIdCapture() {
        return idCapture;
    }
}