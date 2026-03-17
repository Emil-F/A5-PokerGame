package edu.ntnu.idatt2003.emil.a5.model.poker.hand;

import edu.ntnu.idatt2003.emil.a5.model.users.Participant;

public record HandCheckResult(Participant participant, int cardTotal, HandRank rank) {
}
