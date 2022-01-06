package com.rcApp.notificationService.helpers.classes;


import com.rcApp.notificationService.helpers.enums.BloodTypes;
import com.rcApp.notificationService.models.TransfusionQueryDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.rcApp.notificationService.helpers.enums.BloodTypes.*;

public class RcTransfusionCompatibilityHelper {
    private final Map<BloodTypes, List<BloodTypes>> bloodTransfusionCompatibility =
            Map.of(
                    A_NEGATIVE,
                    Arrays.asList(O_NEGATIVE, A_NEGATIVE),
                    A_POSITIVE,
                    Arrays.asList(O_NEGATIVE, O_POSITIVE, A_POSITIVE, A_NEGATIVE),
                    B_NEGATIVE,
                    Arrays.asList(O_NEGATIVE, B_NEGATIVE),
                    B_POSITIVE,
                    Arrays.asList(O_NEGATIVE, O_POSITIVE, B_POSITIVE, B_NEGATIVE),
                    AB_NEGATIVE,
                    Arrays.asList(O_NEGATIVE, B_NEGATIVE, A_NEGATIVE, AB_NEGATIVE),
                    AB_POSITIVE,
                    Arrays.asList(BloodTypes.values()),
                    O_NEGATIVE,
                    Collections.singletonList(O_NEGATIVE),
                    O_POSITIVE,
                    Arrays.asList(O_NEGATIVE, O_POSITIVE));

    private final Map<BloodTypes, List<BloodTypes>> plasmaTransfusionCompatibility =
            Map.of(
                    A_NEGATIVE,
                    Arrays.asList(A_POSITIVE, A_NEGATIVE, AB_NEGATIVE, AB_POSITIVE),
                    A_POSITIVE,
                    Arrays.asList(A_POSITIVE, A_NEGATIVE, AB_NEGATIVE, AB_POSITIVE),
                    B_NEGATIVE,
                    Arrays.asList(B_NEGATIVE, B_POSITIVE, AB_NEGATIVE, AB_POSITIVE),
                    B_POSITIVE,
                    Arrays.asList(B_NEGATIVE, B_POSITIVE, AB_NEGATIVE, AB_POSITIVE),
                    AB_NEGATIVE,
                    Arrays.asList(AB_POSITIVE, AB_NEGATIVE),
                    AB_POSITIVE,
                    Arrays.asList(AB_POSITIVE, AB_NEGATIVE),
                    O_NEGATIVE,
                    Arrays.asList(values()),
                    O_POSITIVE,
                    Arrays.asList(values()));
    private final TransfusionQueryDTO transfusionQueryDTO;

    public RcTransfusionCompatibilityHelper(TransfusionQueryDTO transfusionQueryDTO) {
        this.transfusionQueryDTO = transfusionQueryDTO;
    }

    public List<BloodTypes> getCompatibleBloodTypes() {
        BloodTypes recipientBloodType = transfusionQueryDTO.getRecipient().getBloodType();
        List<BloodTypes> compatibleTypes = Collections.EMPTY_LIST;
        switch (transfusionQueryDTO.getTransfusionType()) {
            case BLOOD:
                compatibleTypes =
                        transfusionQueryDTO.getOnlyRecipientBloodType()
                                ? Collections.singletonList(recipientBloodType)
                                : bloodTransfusionCompatibility.get(recipientBloodType);
                break;
            case BLOOD_PLASMA:
                compatibleTypes =
                        transfusionQueryDTO.getOnlyRecipientBloodType()
                                ? Collections.singletonList(recipientBloodType)
                                : plasmaTransfusionCompatibility.get(recipientBloodType);
                break;
            case PLATELETS:
                compatibleTypes = Collections.singletonList(recipientBloodType);
                break;
        }
        return compatibleTypes;
    }

    public int getNumberOfDonorsToNotify() {
        return transfusionQueryDTO.getRequiredUnits().intValue() + 3;
    }
}
