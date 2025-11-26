package excercises;

/**
 * Record to hold the result of a fuzzy search operation.
 *
 * @param foundExactMatch Indicates whether an exact match was found.
 * @param bestMatchIndex  The index of the best matching value in the array.
 * @param bestMatchValue  The value of the best match.
 */
public record FuzzySearchResult(boolean foundExactMatch, int bestMatchIndex, int bestMatchValue)
{

}