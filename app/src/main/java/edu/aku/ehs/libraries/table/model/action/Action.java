package edu.aku.ehs.libraries.table.model.action;

import edu.aku.ehs.libraries.table.model.ICellData;

public interface Action {
    boolean onAction(ICellData cell);
}
