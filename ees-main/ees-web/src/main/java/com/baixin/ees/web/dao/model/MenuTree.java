package com.baixin.ees.web.dao.model;

import java.util.List;

public class MenuTree {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuTree other = (MenuTree) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MenuTree [text=" + text + "]";
	}
	/**
	 * 菜单名称
	 */
	private String text; 
	/**
	 * null
	 */
	private String href; 
	/**
	 * //菜单ID
	 */
	private String tags; 
	private List<MenuTree> nodes; 
	private State state = new State();
	public MenuTree() {
		super();
	}
	
	public MenuTree(String text, String href, String tags, List<MenuTree> nodes,boolean isChecked) {
		super();
		this.text = text;
		this.href = href;
		this.tags = tags;
		this.nodes = nodes;
		this.state=new State();
		this.state.setChecked(isChecked);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public List<MenuTree> getNodes() {
		return nodes;
	}

	public void setNodes(List<MenuTree> nodes) {
		this.nodes = nodes;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public class State {
		public boolean checked ;
		public boolean disabled = false;
		public boolean expanded = false; 
		public boolean selected = false;
		public boolean isChecked() {
			return checked;
		}
		public void setChecked(boolean checked) {
			this.checked = checked;
		}
		public boolean isDisabled() {
			return disabled;
		}
		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}
		public boolean isExpanded() {
			return expanded;
		}
		public void setExpanded(boolean expanded) {
			this.expanded = expanded;
		}
		public boolean isSelected() {
			return selected;
		}
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
	}
}
