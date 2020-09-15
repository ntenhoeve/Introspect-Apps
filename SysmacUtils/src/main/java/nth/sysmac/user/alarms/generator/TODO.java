//rules.stopMatchingAfter()
//NodeRule replace find with  {@link Rules} getRules()
//NodeRule replace createReplacement with  void removeOrReplaceSearchResult
//	
//E.g.
//
//ATTRIBUTE_PREFIX_RULES=Rules.add(TokenNodePredicate.whiteSpace(),Repetition.zeroOrMore()).
//					.add(TokenNodePredicate.rest(REGEX_SC),Repetition.zeroOrMore))	
//					.add(TokenNodePredicate.equals(),Repetition.zeroOrMore))
//ATTRIBUTE_VALUE_RULES=Rules.add(NodePredicate.anyNode(),Repetition.onceOrMore())
//
//RULE=Rules.addGroup(ATTRIBUTE_PREFIX_RULES)
//	 .addGroup(ATTRIBUTE_VALUE, ATTRIBUTE_VALUE_RULES)
//		.stopMatchingAfter(SkipPageRules.ATTRIBUTE_PREFIX_RULES)
//		
//RULE=Rules.addGroup(RULES).replaceWith(results -> new SkipColumnAttributes(result.getNodes(ATTRIBUTE_PREFIX_RULES))
//
//
//
//Move to Tokenizer and Parser to reflect-util-parser