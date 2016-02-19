package edu.stanford.bmir.protege.web.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import edu.stanford.bmir.protege.web.shared.place.ProjectViewPlace;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 07/04/2013
 */
@WithTokenizers(
        {
                ProjectListPlaceTokenizer.class,
                ProjectViewPlaceTokenizer.class,
                LoginPlaceTokenizer.class,
                SignUpPlaceTokenizer.class
        })
public interface WebProtegePlaceHistoryMapper extends PlaceHistoryMapper {


}
