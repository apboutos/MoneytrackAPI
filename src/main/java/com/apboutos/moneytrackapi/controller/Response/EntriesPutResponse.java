package com.apboutos.moneytrackapi.controller.Response;

import com.apboutos.moneytrackapi.model.Entry;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Data
@ResponseStatus(code = HttpStatus.OK)
public class EntriesPutResponse {

    private final String status;
    private final String result;
    private final String message;
    private final List<Entry> savedEntries;
    private final List<Entry> unsavedDueToOlderVersionEntries;
    private final List<Entry> unsavedDueToErrorEntries;

}
