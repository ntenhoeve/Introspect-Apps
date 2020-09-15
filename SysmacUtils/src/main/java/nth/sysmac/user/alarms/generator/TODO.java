//	
//E.g.
//
//ATTRIBUTE_PREFIX_RULES=MatchRules.add(TokenNodePredicate.whiteSpace(),Repetition.zeroOrMore()).
//					.add(TokenNodePredicate.rest(REGEX_SC),Repetition.zeroOrMore))	
//					.add(TokenNodePredicate.equals(),Repetition.zeroOrMore))
//ATTRIBUTE_VALUE_RULES=MatchRules.add(NodeTypePredicate.anyNode(),Repetition.onceOrMore())
//
//RULE=MatchRules.addGroup(ATTRIBUTE_PREFIX_RULES)
//	 .addGroup(ATTRIBUTE_VALUE, ATTRIBUTE_VALUE_RULES)
//		.stopMatchingAfter(SkipPageRules.ATTRIBUTE_PREFIX_RULES)
//		
//RULE=MatchRules.addGroup(RULES).replaceWith(results -> new SkipColumnAttributes(result.getNodes(ATTRIBUTE_PREFIX_RULES))
//
//
//
//Move to Tokenizer and Parser to reflect-util-parser