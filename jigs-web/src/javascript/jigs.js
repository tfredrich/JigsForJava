function limitLength(element, size)
{
	if (element.value.length > size)
	{
		new Effect.Highlight(element, {startcolor:'#fd8a8a', endcolor:'#ffffff', restorecolor:'#ffffff'});
		element.value = element.value.substring(0, size);
	}
}
