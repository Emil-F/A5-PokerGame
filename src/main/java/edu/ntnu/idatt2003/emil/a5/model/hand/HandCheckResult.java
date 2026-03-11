package edu.ntnu.idatt2003.emil.a5.model.hand;

import java.util.List;

public record HandCheckResult(int cardTotal, List<HandRank> ranks) {}
