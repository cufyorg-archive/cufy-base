/*
 * Copyright (c) 2019, LSafer, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * -You can edit this file (except the header).
 * -If you have change anything in this file. You
 *   shall mention that this file has been edited.
 *   By adding a new header (at the bottom of this header)
 *   with the word "Editor" on top of it.
 */

package cufy.lang;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("JavaDoc")
public class TypeTest {
	@Test(timeout = 50)
	public void in_out_subin_subout() {
		Type type = this.newRange(
				new Class[]{Map.class}, //in
				new Class[]{HashMap.class}, //out
				new Class[]{List.class}, //subin
				new Class[]{CharSequence.class}, //subout
				new Class[]{}//value
		);

		//in
		Assert.assertTrue("Map is absolute included", Type.util.test(type, Map.class));
		Assert.assertFalse("Number is not included anywhere", Type.util.test(type, Number.class));
		//out
		Assert.assertFalse("HashMap is absolute excluded", Type.util.test(type, HashMap.class));
		//subin
		Assert.assertTrue("List is absolute included", Type.util.test(type, List.class));
		Assert.assertTrue("ArrayList is a sub included", Type.util.test(type, ArrayList.class));
		//subout
		Assert.assertFalse("CharSequence is absolute excluded", Type.util.test(type, CharSequence.class));
		Assert.assertFalse("String is sub excluded", Type.util.test(type, String.class));
	}

	private Type newRange(Class<?>[] in, Class<?>[] out, Class<?>[] subin, Class<?>[] subout, Class<?>[] value) {
		return new Type() {
			@Override
			public Class<? extends Annotation> annotationType() {
				return null;
			}

			@Override
			public Class<?>[] in() {
				return in == null ? new Class[0] : in;
			}

			@Override
			public Class<?>[] out() {
				return out == null ? new Class[0] : out;
			}

			@Override
			public Class<?>[] subin() {
				return subin == null ? new Class[0] : subin;
			}

			@Override
			public Class<?>[] subout() {
				return subout == null ? new Class[0] : subout;
			}

			@Override
			public Class<?>[] value() {
				return value == null ? new Class[0] : value;
			}
		};
	}
}
