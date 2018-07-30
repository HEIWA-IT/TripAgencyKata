package com.heiwait.tripagency.infrastructure.repository.mock;

import com.heiwait.tripagency.domain.Destination;
import com.heiwait.tripagency.domain.Trip;
import com.heiwait.tripagency.domain.TripRepositoryPort;
import com.heiwait.tripagency.domain.error.BusinessErrors;
import com.heiwait.tripagency.domain.error.BusinessException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("TripRepositoryMockAdapter")
public class TripRepositoryMockAdapter implements TripRepositoryPort {

    @Override
    public Trip findTripByDestination(final Destination destination) {

        Trip trip = new Trip();
        trip.setDestination(destination);

        switch (destination.getName().toLowerCase()) {
            case "paris":
                trip.setTravelFees(250);
                trip.setAgencyFees(25);
                break;
            case "lille":
                trip.setTravelFees(0);
                trip.setAgencyFees(0);
                break;
            case "new-york":
            case "tokyo":
            case "beijing":
                trip.setTravelFees(1000);
                trip.setAgencyFees(100);
                break;
            default:
                throw new BusinessException(BusinessErrors.MISSING_DESTINATION);
        }

        return trip;
    }
}
